package com.it.academy.controllers;

import com.it.academy.dto.CourseDto;
import com.it.academy.dto.CoursePaginationDto;
import com.it.academy.mappers.CourseMapper;
import com.it.academy.security.DetailsUser;
import com.it.academy.services.CourseService;
import com.it.academy.services.PaymentService;
import com.stripe.exception.StripeException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
@Validated
@Tag(name = "Контроллер для курса")
public class CourseController {
    private final CourseService courseService;
    private final CourseMapper mapper;
    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        List<CourseDto> courses = mapper.map(courseService.getAll());
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long id) {
        CourseDto course = mapper.map(courseService.getById(id));
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping("/current")
    @Operation(summary = "Получение всех купленных курсов текущего пользователя")
    public ResponseEntity<List<CourseDto>> purchasedCoursesOfTheCurrentUser(@AuthenticationPrincipal DetailsUser detailsUser) {
        List<CourseDto> course = mapper.map(courseService.purchasedCoursesOfTheCurrentUser(detailsUser.getUser().getId()));
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("{categoryId}")
    @Operation(summary = "Создание курса",
            description = "Автором курса будет назначен текущий пользователь")
    public ResponseEntity<?> createCourse(@AuthenticationPrincipal DetailsUser detailsUser,
                                          @PathVariable Long categoryId,
                                          @RequestBody @Valid CourseDto course) throws StripeException {
        Long id = courseService.create(detailsUser.getUser().getId(), categoryId, mapper.map(course));
        if (id == null) {
            HttpHeaders headers = new HttpHeaders();
            String link = paymentService.generateOnboardingLink(paymentService.createStripeAccount(detailsUser.getUser().getId()));
            headers.setLocation(URI.create(link));
            return new ResponseEntity<>(headers, HttpStatus.FOUND);
//            String link = paymentService.generateOnboardingLink(paymentService.createStripeAccount(detailsUser.getUser().getId()));
//            return new ResponseEntity<>(link, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{courseId}")
    public ResponseEntity<Long> deleteCourseById(@AuthenticationPrincipal DetailsUser detailsUser,
                                                 @PathVariable Long courseId) {
        Long deletedId = courseService.deleteById(detailsUser.getUser().getId(), courseId);
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{courseId}")
    public ResponseEntity<Long> updateCourseById(@AuthenticationPrincipal DetailsUser detailsUser,
                                                 @PathVariable Long courseId,
                                                 @RequestBody CourseDto course) {
        Long updatedId = courseService.update(detailsUser.getUser().getId(), courseId, mapper.map(course));
        return new ResponseEntity<>(updatedId, HttpStatus.OK);
    }

    @GetMapping("/author/{id}")
    @Operation(summary = "Получение всех курсов по id автора")
    public ResponseEntity<List<CourseDto>> getByAuthorId(@PathVariable("id") Long authorId) {
        List<CourseDto> courses = mapper.map(courseService.getCoursesByAuthor(authorId));
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/filter/price")
    @Operation(summary = "Фильтрация курса по цене и категории",
            description = "По умолчанию фильтрацию будет по возрастанию, " +
                    "для фильтрации по убыванию нужно передать параметр filter как desc")
    public ResponseEntity<CoursePaginationDto> priceFilter(
            @RequestParam Long categoryId,
            @RequestParam(defaultValue = "1") @Min(value = 1) Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "ask")
            @Parameter(description = "Тип фильтрации по возрастанию и по убыванию") String filter) {
        List<CourseDto> courses = filter.equalsIgnoreCase("desc")
                ? mapper.map(courseService.filterByPriceDesc(categoryId, pageNumber, pageSize))
                : mapper.map(courseService.filterByPriceAsk(categoryId, pageNumber, pageSize));
        CoursePaginationDto coursePaginationDto = CoursePaginationDto.builder()
                .courses(courses)
                .amountPage(filter.equalsIgnoreCase("desc")
                        ? IntStream.rangeClosed(1, (courseService
                                .filterByPriceDesc(categoryId, 1, courseService.getAll().size()).size() + 9) / pageSize)
                        .boxed().collect(Collectors.toList())
                        : IntStream.rangeClosed(1, (courseService
                                .filterByPriceAsk(categoryId, 1, courseService.getAll().size()).size() + 9) / pageSize)
                        .boxed().collect(Collectors.toList()))
                .build();

        return new ResponseEntity<>(coursePaginationDto, HttpStatus.OK);
    }

    @GetMapping("/language")
    @Operation(summary = "Получение всех курсов по определенному языку и категории")
    public ResponseEntity<CoursePaginationDto> getByLanguage(
            @RequestParam String language,
            @RequestParam Long categoryId,
            @RequestParam(defaultValue = "1") @Min(value = 1) Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        List<CourseDto> courses =
                mapper.map(courseService.getCoursesByLanguage(language, categoryId, pageNumber, pageSize));
        CoursePaginationDto coursePaginationDto = CoursePaginationDto.builder()
                .courses(courses)
                .amountPage(IntStream.rangeClosed(1, (courseService
                                .getCoursesByLanguage(language, categoryId, 1, courseService.getAll().size()).size() + 9) / pageSize)
                        .boxed().collect(Collectors.toList()))
                .build();

        return new ResponseEntity<>(coursePaginationDto, HttpStatus.OK);
    }

    @GetMapping("/category")
    @Operation(summary = "Получение всех курсов по определенной категории")
    public ResponseEntity<CoursePaginationDto> getCourseByCategoryId(
            @RequestParam Long categoryId,
            @RequestParam(defaultValue = "1") @Min(value = 1) Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        List<CourseDto> courses = mapper.map(courseService.getCoursesByCategory(categoryId, pageNumber, pageSize));

        CoursePaginationDto coursePaginationDto = CoursePaginationDto.builder()
                .courses(courses)
                .amountPage(IntStream.rangeClosed(1, (courseService
                                .getCoursesByCategory(categoryId, 1, courseService.getAll().size()).size() + 9) / pageSize)
                        .boxed().collect(Collectors.toList()))
                .build();
        return new ResponseEntity<>(coursePaginationDto, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Получение курсов по имени")
    public ResponseEntity<List<CourseDto>> getCourseByName(@PathVariable String name) {
        List<CourseDto> courses = mapper.map(courseService.getCoursesByName(name));
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/duration/{courseId}")
    @Operation(summary = "Получение длительности курса по его id")
    public ResponseEntity<Double> getCourseDurationSum(@PathVariable Long courseId) {
        Double durationSun = courseService.getCourseDuration(courseId);
        return new ResponseEntity<>(durationSun, HttpStatus.OK);
    }

    @GetMapping("/count")
    @Operation(summary = "Получение количества всех курсов")
    public ResponseEntity<Integer> getCountOfAllCourses() {
        Integer courseCount = courseService.getCountOfAllCourses();
        return new ResponseEntity<>(courseCount, HttpStatus.OK);
    }

}

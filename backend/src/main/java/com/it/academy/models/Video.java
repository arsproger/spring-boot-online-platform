package com.it.academy.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "videos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
public class Video implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private double size;
    private String url;
    private Date created;
    private Date modified;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    @ToString.Exclude
    private Lesson lesson;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Video video = (Video) o;
        return getId() != null && Objects.equals(getId(), video.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
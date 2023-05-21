import React from "react";

import Categories from "@/components/Categories/Categories";
import Hero from "@/components/Hero/Hero";
import Statistics from "@/components/Statistics/Statistics";
import RecommendedSiders from "@/components/RecommendedSiders/RecommendedSiders";

const Main = () => {
  return (
    <>
      <Hero />
      <Statistics />
      <Categories />
      <RecommendedSiders />
    </>
  );
};

export default Main;

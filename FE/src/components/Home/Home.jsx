import React, { useEffect } from "react";
import "./Home.css";
import MultiItemCarousel from "./MultiItemCarousel.jsx";
import RestaurantCard from "../Restaurant/RestaurantCard.jsx";
import { useDispatch, useSelector } from "react-redux";
import { getAllRestaurantsAction } from "../State/Restaurant/Action.js";

const restaurant = [1, 1, 1, 1, 1, 1, 1, 1];
const Home = () => {
  const dispatch = useDispatch();
  const jwt = localStorage.getItem("jwt");
  const { restaurant } = useSelector((store) => store);
  useEffect(() => {
    dispatch(getAllRestaurantsAction(jwt));
  }, [jwt]);
  console.log(restaurant);
  return (
    <div className="pb-10">
      <section className="relative flex flex-col items-center justify-center banner -z-50">
        <div className="w-[50vw] z-10 text-center">
          <p className="z-10 py-5 text-2xl font-bold lg:text-6xl">KNBC Food</p>
          <p className="z-10 text-xl text-gray-300 lg:text-4xl">
            Taste the Convenience: Food, Fast and Delivered
          </p>
        </div>

        <div className="absolute top-0 left-0 right-0 cover"></div>
      </section>
      <section className="p-10 lg:py-10 lg:px-20">
        <p className="py-3 pb-10 text-2xl font-semibold text-gray-400">
          Top meals
        </p>
        <MultiItemCarousel></MultiItemCarousel>
      </section>
      <section className="px-5 pt-10 lg:px-20">
        <h1 className="pb-8 text-2xl font-semibold text-gray-400">
          Order From Our Handpicked Favorites
        </h1>
        <div className="flex flex-wrap items-center justify-around gap-5">
          {/* {restaurant.map((item) => (
            <RestaurantCard />
          ))} */}
        </div>
      </section>
    </div>
  );
};

export default Home;

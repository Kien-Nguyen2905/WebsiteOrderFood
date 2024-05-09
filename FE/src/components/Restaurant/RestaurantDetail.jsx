import { Divider, FormControl, FormControlLabel, Grid, Radio, RadioGroup, Typography } from "@mui/material";
import React, { useState } from "react";
import LocationOnSharpIcon from '@mui/icons-material/LocationOnSharp';
import CalendarTodaySharpIcon from '@mui/icons-material/CalendarTodaySharp';
import MenuCard from "./MenuCard";

const categories = [
    "pizza",
    "biryani",
    "burger",
    "chicken",
    "rice"
]
const foodTypes = [
    { label: "All", value: "all" },
    { label: "Vegetarian only", value: "vegetarian" },
    { label: "Non-Vegetarian", value: "non_vegetarian" },
    { label: "Seasonal", value: "seasonal" }
]

const menu = [1,1,1,1,1,1]

const RestaurantDetail = () => {
    const [foodType, setFoodType] = useState("all")
    const handleFilter = (e) => {
        console.log(e.target.value, e.target.name)
    }
    return (
        <div className="px-5 lg:px-20">
            <section>
                <h3 className="text-gray-500 py-2 mt-10">Home/india/indian fast food/3</h3>
                <div>
                    <Grid container spacing={2}>
                        <Grid item xs={12}>
                            <img className="w-full h-[40vh] object-cover"
                                src="https://images.pexels.com/photos/1058277/pexels-photo-1058277.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" alt="" />
                        </Grid>
                        <Grid item xs={12} lg={6}>
                            <img className="w-full h-[40vh] object-cover"
                                src="https://images.pexels.com/photos/1126728/pexels-photo-1126728.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" alt="" />
                        </Grid>
                        <Grid item xs={12} lg={6}>
                            <img className="w-full h-[40vh] object-cover"
                                src="https://images.pexels.com/photos/1307698/pexels-photo-1307698.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" alt="" />
                        </Grid>
                    </Grid>
                </div>
                <div className="pt-3 pb-5">
                    <h1 className="text-4xl font-semibold">Indian Fast Food</h1>
                    <p className="text-gray-500 mt-1">
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                        Ducimus consectetur eligendi delectus aut eaque numquam
                        sed quaerat cum officia, accusantium, sunt dicta ipsum
                        repellendus nulla in facere voluptas maiores blanditiis!
                    </p>
                    <div className="space-y-3 mt-3">
                        <p className="text-gray-500 flex items-center gap-3">
                            <LocationOnSharpIcon />
                            <span>
                                Mumbai, Maharstra
                            </span>
                        </p>
                        <p className="text-gray-500 flex items-center gap-3">
                            <CalendarTodaySharpIcon />
                            <span>
                                Lorem ipsum, dolor sit amet consectetur adipisicing elit.
                                Quas totam aliquid odio corporis deleniti qui minima laudantium
                                eaque eveniet perferendis vero maxime quasi, temporibus
                                doloremque harum et? Quae, animi qui.
                            </span>
                        </p>
                    </div>
                </div>
            </section>

            <Divider />
            <section className="pt-[2rem] lg:flex relative">
                <div className="space-y-10 lg:w-[20%] filter">
                    <div className="box space-y-5 lg:sticky top-28">
                        <div>
                            <Typography variant="h5" sx={{ paddingBottom: "1rem" }}>
                                Food type
                            </Typography>
                            <FormControl className="py-10 space-y-5" component={"fieldset"}>
                                <RadioGroup onChange={handleFilter} name="food-type" value={foodType}>
                                    {foodTypes.map((item) => (
                                        <FormControlLabel
                                            key={item.value}
                                            value={item.value}
                                            control={<Radio />}
                                            label={item.label}
                                        />
                                    ))}
                                </RadioGroup>
                            </FormControl>
                        </div>
                        <Divider/>
                        <div>
                            <Typography variant="h5" sx={{ paddingBottom: "1rem" }}>
                                Food Category
                            </Typography>
                            <FormControl className="py-10 space-y-5" component={"fieldset"}>
                                <RadioGroup onChange={handleFilter} name="food-type" value={foodType}>
                                    {categories.map((item) => (
                                        <FormControlLabel
                                            key={item}
                                            value={item}
                                            control={<Radio />}
                                            label={item}
                                        />
                                    ))}
                                </RadioGroup>
                            </FormControl>
                        </div>
                    </div>
                </div>

                <div className="space-y-5 lg:w-[80%] lg:pl-10">
                    {menu.map((item) => <MenuCard/>)}
                </div>

            </section>
        </div>
    )
}

export default RestaurantDetail
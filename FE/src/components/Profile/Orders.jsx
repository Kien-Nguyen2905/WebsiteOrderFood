import React from "react";
import OrderCard from "./OrderCard";

const Orders = () => {
  return (
    <div className="flex flex-col items-center">
      <h1 className="text-xl font-semibold text-center py-7">My orders</h1>
      <div className="w-full space-y-5 lg:w-1/2">
        {[1, 1, 1, 1].map((item) => (
          <OrderCard></OrderCard>
        ))}
      </div>
    </div>
  );
};

export default Orders;

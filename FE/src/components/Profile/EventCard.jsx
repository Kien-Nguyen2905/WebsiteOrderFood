import {
  Card,
  CardActions,
  CardContent,
  CardMedia,
  IconButton,
  Typography,
} from "@mui/material";
import React from "react";
import DeleteIcon from "@mui/icons-material/Delete";
const EventCard = () => {
  return (
    <div>
      <Card sx={{ width: 345 }}>
        <CardMedia
          sx={{ height: 345 }}
          image="https://plus.unsplash.com/premium_photo-1669687759693-52ba5f9fa7a8?q=80&w=2788&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ></CardMedia>
        <CardContent>
          <Typography variant="h5">Indian Fast Food</Typography>
          <Typography variant="body2">50% off on your first order</Typography>
          <div className="py-2 space-y-2">
            <p>{"mumbai"}</p>
            <p className="text-sm text-blue-500">Februry 14, 2024 12:00 AM</p>
            <p className="text-sm text-red-500">Februry 18, 2024 12:00 AM</p>
          </div>
        </CardContent>
        {false && (
          <>
            <CardActions>
              <IconButton>
                <DeleteIcon></DeleteIcon>
              </IconButton>
            </CardActions>
          </>
        )}
      </Card>
    </div>
  );
};

export default EventCard;

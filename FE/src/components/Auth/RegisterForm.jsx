import { Button, MenuItem, Select, TextField, Typography } from "@mui/material";
import { Field, Form, Formik } from "formik";
import React from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { registerUser } from "../State/Authentication/Action";

const initialValues = {
  fullName: "",
  email: "",
  password: "",
  phone: "",
  birthday: "",
  role: "CUSTOMER",
};
const RegisterForm = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const handleSubmit = (values) => {
    console.log(values);
    dispatch(registerUser({ userData: values, navigate }));
  };
  return (
    <div>
      <Typography variant="h5" className="text-center">
        Register
      </Typography>

      <Formik onSubmit={handleSubmit} initialValues={initialValues}>
        <Form>
          <Field
            as={TextField}
            name="fullName"
            label="full name"
            fullWidth
            variant="outlined"
            margin="normal"
          ></Field>

          <Field
            as={TextField}
            name="email"
            label="email"
            fullWidth
            variant="outlined"
            margin="normal"
          ></Field>

          <Field
            as={TextField}
            name="password"
            label="password"
            fullWidth
            variant="outlined"
            margin="normal"
            type="password"
          ></Field>

          <Field
            fullWidth
            as={Select}
            labelId="role-simple-select-label"
            id="demo-simple-select"
            name="role"
            margin="normal"
            // value={age}
            // onChange={handleChange}
          >
            <MenuItem value={"CUSTOMER"}>Customer</MenuItem>
            <MenuItem value={"RESTAURANT_OWNER"}>Restaurant Owner</MenuItem>
          </Field>
          <Button
            sx={{ mt: 2, padding: "1rem" }}
            fullWidth
            type="submit"
            variant="contained"
          >
            Register
          </Button>
        </Form>
      </Formik>
      <Typography variant="body2" algin="center" sx={{ mt: 3 }}>
        If you have an account already?
        <Button
          size="small"
          onClick={() => {
            navigate("/account/login");
          }}
        >
          Login
        </Button>
      </Typography>
    </div>
  );
};

export default RegisterForm;

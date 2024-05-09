import React from "react";

import CartItem from "./CartItem";
import { Box, Button, Card, Divider, Grid, Modal, TextField } from "@mui/material";
import AddressCart from "./AddressCard";
import AddLocationIcon from '@mui/icons-material/AddLocation';
import { ErrorMessage, Field, Form, Formik } from "formik";
// import * as Yup from "yup"

const items = [1, 1]
const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    outline: "none",
    boxShadow: 24,
    p: 4,
};
const initialValues = {
    streetAddress: "",
    state: "",
    pincode: "",
    city: ""
}
// const validationSchema = Yup.object.shape({
//     streetAddress: Yup.String().required("Street address is required"),
//     state: Yup.String().required("State is required"),
//     pincode: Yup.required("Pincode is required"),
//     city: Yup.String().required("City is required")
// })
const Cart = () => {
    const createOrderUsingSelectAddress = () => { }
    const handleOpenAddressModal = () => setOpen(true);

    const [open, setOpen] = React.useState(false);
    const handleClose = () => setOpen(false);
    const handleSubmit = (values) => {
        console.log("form value ", values);
    }
    return (
        <div>
            <main className="lg:flex justify-between">
                <section className="lg:w-[30%] space-y-6 lg:min-h-screen pt-10">
                    {items.map(() => <CartItem />)}
                    <Divider />
                    <div className="billDetails px-5 text-sm">
                        <p className="font-extralight py-5">Bill Details</p>
                        <div className="space-y-3">
                            <div className="flex justify-between text-gray-400">
                                <p>Item total</p>
                                <p>$799</p>
                            </div>
                            <div className="flex justify-between text-gray-400">
                                <p>Deliver Fee</p>
                                <p>$29</p>
                            </div>
                            <div className="flex justify-between text-gray-400">
                                <p>GST and Restaurant Charges</p>
                                <p>$29</p>
                            </div>
                            <Divider />
                        </div>

                        <div className="flex justify-between text-gray-400">
                            <p>Total pay</p>
                            <p>$10000</p>
                        </div>
                    </div>
                </section>

                <Divider orientation="vertical" flexItem />

                <section className="lg:w-[70%] flex justify-center px-5 pb-10 lg:pb-0">
                    <div>
                        <h1 className="text-center font-semibold text-2xl py-10">Choose Delivery Addess</h1>
                        <div className="flex gap-5 flex-wrap justify-center">
                            {[1, 1, 1, 1, 1].map((item) => <AddressCart handleSelectAddress={createOrderUsingSelectAddress} item={item} showButton={true} />)}
                            <Card className="flex gap-5 w-64 p-5">
                                <AddLocationIcon />
                                <div className="space-y-3 text-gray-500">
                                    <h1 className="font-semibold text-lg text-white">Add New Address</h1>
                                    {(<Button variant="outlined" fullWidth onClick={handleOpenAddressModal}>Add</Button>)}
                                </div>
                            </Card>
                        </div>
                    </div>
                </section>
            </main>
            <Modal
                open={open}
                onClose={handleClose}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <Box sx={style}>
                    <Formik initialValues={initialValues}
                        // validationSchema={validationSchema}
                        onSubmit={handleSubmit}
                    >
                        <Form>
                            <Grid container spacing={2}>
                                <Grid item xs={12}>
                                    <Field
                                        as={TextField}
                                        name="streetAddress"
                                        label="Street Address"
                                        fullWidth variant="outlined"
                                        error={!ErrorMessage("Street address")}
                                    // helperText={
                                    //     <ErrorMessage>
                                    //         {(msg) => <span className="text-red-600">{msg}</span>}
                                    //     </ErrorMessage>
                                    // }
                                    >
                                    </Field>
                                </Grid>
                                <Grid item xs={12}>
                                    <Field
                                        as={TextField}
                                        name="state"
                                        label="State"
                                        fullWidth variant="outlined"
                                        error={!ErrorMessage("State")}
                                    // helperText={
                                    //     <ErrorMessage>
                                    //         {(msg) => <span className="text-red-600">{msg}</span>}
                                    //     </ErrorMessage>
                                    // }
                                    >
                                    </Field>
                                </Grid>
                                <Grid item xs={12}>
                                    <Field
                                        as={TextField}
                                        name="city"
                                        label="City"
                                        fullWidth variant="outlined"
                                        error={!ErrorMessage("City")}
                                    // helperText={
                                    //     <ErrorMessage>
                                    //         {(msg) => <span className="text-red-600">{msg}</span>}
                                    //     </ErrorMessage>
                                    // }
                                    >
                                    </Field>
                                </Grid>
                                <Grid item xs={12}>
                                    <Field
                                        as={TextField}
                                        name="pincode"
                                        label="Pincode"
                                        fullWidth variant="outlined"
                                        error={!ErrorMessage("Pincode")}
                                    // helperText={
                                    //     <ErrorMessage>
                                    //         {(msg) => <span className="text-red-600">{msg}</span>}
                                    //     </ErrorMessage>
                                    // }
                                    >
                                    </Field>
                                </Grid>
                                <Grid item xs={12}>
                                    <Button fullWidth variant="contained" type="submit" color="primary">Deliver Here</Button>
                                </Grid>
                            </Grid>
                        </Form>
                    </Formik>
                </Box>
            </Modal>
        </div >
    )
}

export default Cart


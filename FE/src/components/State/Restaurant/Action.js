import { api } from "../../config/api"
import {
    CREATE_RESTAURANT_REQUEST,
    CREATE_RESTAURANT_SUCCESS,
    CREATE_RESTAURANT_FAILURE,
    GET_ALL_RESTAURANTS_REQUEST,
    GET_ALL_RESTAURANTS_SUCCESS,
    GET_ALL_RESTAURANTS_FAILURE,
    DELETE_RESTAURANT_REQUEST,
    DELETE_RESTAURANT_SUCCESS,
    DELETE_RESTAURANT_FAILURE,
    UPDATE_RESTAURANT_REQUEST,
    UPDATE_RESTAURANT_SUCCESS,
    UPDATE_RESTAURANT_FAILURE,
    GET_RESTAURANTS_BY_ID_REQUEST,
    GET_RESTAURANTS_BY_ID_SUCCESS,
    GET_RESTAURANTS_BY_ID_FAILURE,
    GET_RESTAURANTS_BY_USER_ID_REQUEST,
    GET_RESTAURANTS_BY_USER_ID_SUCCESS,
    GET_RESTAURANTS_BY_USER_ID_FAILURE,
    UPDATE_RESTAURANTS_STATUS_REQUEST,
    UPDATE_RESTAURANTS_STATUS_SUCCESS,
    UPDATE_RESTAURANTS_STATUS_FAILURE,
    CREATE_EVENTS_REQUEST,
    CREATE_EVENTS_SUCCESS,
    CREATE_EVENTS_FAILURE,
    GET_ALL_EVENTS_REQUEST,
    GET_ALL_EVENTS_SUCCESS,
    GET_ALL_EVENTS_FAILURE,
    DELETE_EVENTS_REQUEST,
    DELETE_EVENTS_SUCCESS,
    DELETE_EVENTS_FAILURE,
    GET_RESTAURANTS_EVENTS_REQUEST,
    GET_RESTAURANTS_EVENTS_SUCCESS,
    GET_RESTAURANTS_EVENTS_FAILURE,
    CREATE_CATEGORY_REQUEST,
    CREATE_CATEGORY_SUCCESS,
    CREATE_CATEGORY_FAILURE,
    GET_RESTAURANTS_CATEGORY_REQUEST,
    GET_RESTAURANTS_CATEGORY_SUCCESS,
    GET_RESTAURANTS_CATEGORY_FAILURE
} from "./ActionTypes"

export const getAllRestaurantsAction = (token) => {
    return async (dispatch) => {
        dispatch({ type: GET_ALL_RESTAURANTS_REQUEST });
        try {
            const { data } = await api.get("/api/restaurants", {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            dispatch({ type: GET_ALL_RESTAURANTS_SUCCESS, payload: data })
            console.log("all restaurant", data);
        }
        catch (error) {
            console.log("catch error", error);
            dispatch({ type: GET_ALL_RESTAURANTS_FAILURE, payload: error })
        }
    }
}

export const getRestaurantById = (reqData) => {
    return async (dispatch) => {
        dispatch({ type: GET_RESTAURANTS_BY_ID_REQUEST });
        try {
            const response = await api.get(`/api/restaurants/${reqData.restaurantId}`, {
                headers: {
                    Authorization: `Bearer ${reqData.jwt}`
                }
            });
            dispatch({ type: GET_RESTAURANTS_BY_ID_SUCCESS, payload: response.data })
        }
        catch (error) {
            console.log("error", error);
            dispatch({ type: GET_RESTAURANTS_BY_ID_FAILURE, payload: error })
        }
    }
}

export const getRestaurantsByUserId = (jwt) => {
    return async (dispatch) => {
        dispatch({ type: GET_RESTAURANTS_BY_USER_ID_REQUEST });
        try {
            const { data } = await api.get(`/api/admin/restaurants/user`, {
                headers: {
                    Authorization: `Bearer ${jwt}`
                }
            });
            console.log("get restaurant by user id", data);
            dispatch({ type: GET_RESTAURANTS_BY_USER_ID_SUCCESS, payload: data })
        }
        catch (error) {
            console.log("catch error", error);
            dispatch({ type: GET_RESTAURANTS_BY_USER_ID_FAILURE, payload: error.message })
        }
    }
}

export const createRestaurant = (reqData) => {
    console.log("token----------------", reqData.token);
    return async (dispatch) => {
        dispatch({ type: CREATE_RESTAURANT_REQUEST });
        try {
            const { data } = await api.get(`/api/admin/restaurants`, reqData.data, {
                headers: {
                    Authorization: `Bearer ${reqData.token}`
                }
            });
            dispatch({ type: CREATE_RESTAURANT_SUCCESS, payload: data })
            console.log("created restaurant", data);
        }
        catch (error) {
            console.log("catch error", error);
            dispatch({ type: CREATE_RESTAURANT_FAILURE, payload: error })
        }
    }
}

export const updateRestaurant = ({ restaurantId, restaurantData, jwt }) => {
    return async (dispatch) => {
        dispatch({ type: UPDATE_RESTAURANTS_STATUS_REQUEST });
        try {
            const res = await api.put(`/api/admin/restaurants/${restaurantId}`,
                restaurantData, {
                headers: {
                    Authorization: `Bearer ${jwt}`
                }
            });
            dispatch({ type: UPDATE_RESTAURANTS_STATUS_SUCCESS, payload: res.data })
        }
        catch (error) {
            console.log("catch error", error);
            dispatch({ type: UPDATE_RESTAURANTS_STATUS_FAILURE, payload: error })
        }
    }
}

export const deleteRestaurant = ({ restaurantId, jwt }) => {
    return async (dispatch) => {
        dispatch({ type: DELETE_RESTAURANT_REQUEST });
        try {
            const res = await api.delete(`/api/admin/restaurants/${restaurantId}`, {
                headers: {
                    Authorization: `Bearer ${jwt}`
                }
            });
            console.log("delete restaurant", res.data);
            dispatch({ type: DELETE_RESTAURANT_SUCCESS, payload: res.data })
        }
        catch (error) {
            console.log("catch error", error);
            dispatch({ type: DELETE_RESTAURANT_FAILURE, payload: error })
        }
    }
}

export const updateRestaurantStatus = ({ restaurantId, jwt }) => {
    return async (dispatch) => {
        dispatch({ type: UPDATE_RESTAURANTS_STATUS_REQUEST });
        try {
            const res = await api.put(`/api/admin/restaurants/${restaurantId}/status`,
                {},
                {
                    headers: {
                        Authorization: `Bearer ${jwt}`
                    }
                });
            console.log("update restaurant", res.data);
            dispatch({ type: UPDATE_RESTAURANTS_STATUS_SUCCESS, payload: res.data })
        }
        catch (error) {
            console.log("error", error);
            dispatch({ type: UPDATE_RESTAURANTS_STATUS_FAILURE, payload: error })
        }
    }
}

export const createEvenetAction = ({ data, restaurantId, jwt }) => {
    return async (dispatch) => {
        dispatch({ type: CREATE_EVENTS_REQUEST });
        try {
            const res = await api.post(`/api/admin/events/restaurant/${restaurantId}`,
                data,
                {
                    headers: {
                        Authorization: `Bearer ${jwt}`
                    }
                });
            console.log("create events", res.data);
            dispatch({ type: CREATE_EVENTS_SUCCESS, payload: res.data })
        }
        catch (error) {
            console.log("catch - ", error);
            dispatch({ type: CREATE_EVENTS_FAILURE, payload: error })
        }
    }
}

export const getAllEvents = ({ jwt }) => {
    return async (dispatch) => {
        dispatch({ type: GET_ALL_EVENTS_REQUEST });
        try {
            const res = await api.get(`/api/events`,
                {
                    headers: {
                        Authorization: `Bearer ${jwt}`
                    }
                });
            console.log("get all events", res.data);
            dispatch({ type: GET_ALL_EVENTS_SUCCESS, payload: res.data })
        }
        catch (error) {
            dispatch({ type: GET_ALL_EVENTS_FAILURE, payload: error })
        }
    }
}

export const deleteEventAction = ({ eventId, jwt }) => {
    return async (dispatch) => {
        dispatch({ type: DELETE_EVENTS_REQUEST });
        try {
            const res = await api.delete(`/api/admin/events/${eventId}`,
                {
                    headers: {
                        Authorization: `Bearer ${jwt}`
                    }
                });
            console.log("delete events", res.data);
            dispatch({ type: DELETE_EVENTS_SUCCESS, payload: eventId })
        }
        catch (error) {
            console.log("catch - ", error);
            dispatch({ type: DELETE_EVENTS_FAILURE, payload: error })
        }
    }
}

export const getRestaurantsEvents = ({ restaurantId, jwt }) => {
    return async (dispatch) => {
        dispatch({ type: GET_RESTAURANTS_EVENTS_REQUEST });
        try {
            const res = await api.get(`/api/admin/events/${restaurantId}`,
                {
                    headers: {
                        Authorization: `Bearer ${jwt}`
                    }
                });
            console.log("get restaurants events", res.data);
            dispatch({ type: GET_RESTAURANTS_EVENTS_SUCCESS, payload: res.data })
        }
        catch (error) {
            console.log("catch - ", error);
            dispatch({ type: GET_RESTAURANTS_EVENTS_FAILURE, payload: error })
        }
    }
}

export const createCategoryAction = ({ reqData, jwt }) => {
    return async (dispatch) => {
        dispatch({ type: CREATE_CATEGORY_REQUEST });
        try {
            const res = await api.post(`/api/admin/category`,
                reqData,
                {
                    headers: {
                        Authorization: `Bearer ${jwt}`
                    }
                });
            console.log("create category", res.data);
            dispatch({ type: CREATE_CATEGORY_SUCCESS, payload: res.data })
        }
        catch (error) {
            console.log("catch - ", error);
            dispatch({ type: CREATE_CATEGORY_FAILURE, payload: error })
        }
    }
}

export const getRestaurantCategory = ({ jwt, restaurantId }) => {
    return async (dispatch) => {
        dispatch({ type: GET_RESTAURANTS_CATEGORY_REQUEST });
        try {
            const res = await api.get(`/api/category/restaurant/${restaurantId}`,
                {
                    headers: {
                        Authorization: `Bearer ${jwt}`
                    }
                });
            console.log("get restaurants category", res.data);
            dispatch({ type: GET_RESTAURANTS_CATEGORY_SUCCESS, payload: res.data })
        }
        catch (error) {
            console.log("catch - ", error);
            dispatch({ type: GET_RESTAURANTS_CATEGORY_FAILURE, payload: error })
        }
    }
}
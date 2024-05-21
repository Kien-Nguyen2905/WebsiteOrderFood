import { api } from "../../config/api"
import {
    GET_INGREDIENTS,
    UPDATE_STOCK,
    CREATE_INGREDIENT_SUCCESS,
    CREATE_INGREDIENT_CATEGORY_SUCCESS,
    GET_INGREDIENT_CATEGORY_SUCCESS
} from "./ActionTypes"

export const getIngredientsOfRestaurant = ({ id, jwt }) => {
    return async (dispatch) => {
        try {
            const response = await api.get(
                `/api/admin/ingredients/restaurant/${id}`,
                {
                    headers: {
                        Authorization: `Bearer ${jwt} `,
                    }
                })
            console.log("get all ingredients ", response.data);
            dispatch({ type: GET_INGREDIENTS, payload: response.data });
        }
        catch (error) {
            console.log("error", error);
            // Handle error, dispatch an error action, etc.
        }
    }
}

export const createIngredient = ({ data, jwt }) => {
    return async (dispatch) => {
        try {
            const response = await api.get(
                `/api/admin/ingredients/restaurant`,
                data,
                {
                    headers: {
                        Authorization: `Bearer ${jwt} `,
                    }
                })
            console.log("creata ingredients ", response.data);
            dispatch({ type: CREATE_INGREDIENT_SUCCESS, payload: response.data });
        }
        catch (error) {
            console.log("error", error);
            // Handle error, dispatch an error action, etc.
        }
    }
}

export const createIngredientCategory = ({ data, jwt }) => {
    console.log("data ", data, "jwt ", jwt);
    return async (dispatch) => {
        try {
            const response = await api.post(
                `/api/admin/ingredients/category`,
                data,
                {
                    headers: {
                        Authorization: `Bearer ${jwt} `,
                    }
                })
            console.log("create ingredients category", response.data);
            dispatch({ type: CREATE_INGREDIENT_CATEGORY_SUCCESS, payload: response.data });
        }
        catch (error) {
            console.log("error", error);
            // Handle error, dispatch an error action, etc.
        }
    }
}

export const getIngredientCategory = ({ id, jwt }) => {
    return async (dispatch) => {
        try {
            const response = await api.get(
                `/api/admin/ingredients/restaurant/${id}/category`,
                {
                    headers: {
                        Authorization: `Bearer ${jwt} `,
                    }
                })
            console.log("get ingredients category", response.data);
            dispatch({ type: GET_INGREDIENT_CATEGORY_SUCCESS, payload: response.data });
        }
        catch (error) {
            console.log("error", error);
            // Handle error, dispatch an error action, etc.
        }
    }
}

export const updateStockOfIngredient = ({ id, jwt }) => {
    return async (dispatch) => {
        try {
            const response = await api.put(
                `/api/admin/ingredients/restaurant/${id}/stoke`,
                {},
                {
                    headers: {
                        Authorization: `Bearer ${jwt} `,
                    }
                })
            console.log("update ingredients category", response.data);
            dispatch({ type: UPDATE_STOCK, payload: response.data });
        }
        catch (error) {
            console.log("error", error);
            // Handle error, dispatch an error action, etc.
        }
    }
}
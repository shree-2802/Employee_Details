import { configureStore } from "@reduxjs/toolkit";
import adminSlice from "./slice/adminSlice";
import { useDispatch } from "react-redux";

export const store = configureStore({
    reducer: {
        adminSlice
    }
})

export const useAppDispatch = () => useDispatch<typeof store.dispatch>();
export type RootState = ReturnType<typeof store.getState>;
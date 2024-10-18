import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Pages } from "../pages";

export const Routing = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Pages.Home />} />
                <Route path="/login" element={<Pages.Login />} />
                <Route path="/forgotPassword" element={<Pages.ForgotPassword />} />
                <Route path="*" element={<Pages.PageNotFound/>}/>
            </Routes>
        </BrowserRouter>
    )
}
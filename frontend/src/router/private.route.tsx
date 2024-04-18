import { Outlet, Navigate } from "react-router-dom";

export const PrivateRoute = () => {
  //   const { isAuthenticated } = useAuth();
  const isAuthenticated = localStorage.getItem("access_token");

  return isAuthenticated ? <Outlet /> : <Navigate to="/login" replace />;
};

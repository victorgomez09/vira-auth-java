import { Outlet, Navigate } from "react-router-dom";

export const PrivateRoute = () => {
  //   const { isAuthenticated } = useAuth();
  const isAuthenticated = true;

  return isAuthenticated ? <Outlet /> : <Navigate to="/login" replace />;
};

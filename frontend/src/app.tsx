import { Route, Routes } from "react-router-dom";
import LoginView from "./views/login.view";
import RegisterView from "./views/register.view";
import DocsLayout from "./components/layouts/docs.layout";
import DocsView from "./views/docs/docs.view";
import { PrivateRoute } from "./router/private.route";

function App() {
  return (
    <div className="bg-base-100">
      <Routes>
        <Route path="/register" element={<RegisterView />} />
        <Route path="/login" element={<LoginView />} />
        <Route path="/docs" element={<PrivateRoute />}>
          <Route path="" element={<DocsView />} />
        </Route>
      </Routes>
    </div>
  );
}

export default App;

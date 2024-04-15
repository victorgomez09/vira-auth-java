import { Routes, Route } from "react-router-dom";
import EditorView from "./views/docs/editor/editor.view";
import LoginView from "./views/login.view";

function App() {
  return (
    <Routes>
      <Route path="/login" element={<LoginView />} />
      <Route path="/docs" element={<EditorView />} />
    </Routes>
  );
}

export default App;

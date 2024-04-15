import { Routes, Route } from 'react-router-dom'
import EditorView from './views/editor.view'


function App() {
  return (
    <Routes>
      <Route path='/' element={<EditorView />} />
    </Routes>
  )
}

export default App

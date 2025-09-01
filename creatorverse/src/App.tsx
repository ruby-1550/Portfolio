import React, { useEffect, useState } from 'react'
import { supabase } from "./client.tsx";
import Card from "./components/Card"

import './App.css'
import { useRoutes } from "react-router-dom";

import ShowCreators from "./pages/ShowCreators";
import ViewCreator from "./pages/ViewCreator";
import EditCreator from "./pages/EditCreator";
import AddCreator from "./pages/AddCreator";




const App = () => {

  const routes = useRoutes([
    { path: "/", element: <ShowCreators /> },
    { path: "/creator/:id", element: <ViewCreator /> },
    { path: "/creator/:id/edit", element: <EditCreator /> },
    { path: "/new", element: <AddCreator /> },
  ]);




  return (
    <div className="creatorverse">
      {routes}

    </div>

  )
}

export default App

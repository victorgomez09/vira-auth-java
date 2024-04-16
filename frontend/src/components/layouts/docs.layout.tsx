import { Outlet } from "react-router-dom";

export default function DocsLayout() {
    return (
        <div className="drawer drawer-open">
            <input id="my-drawer-2" type="checkbox" className="drawer-toggle" />
            <div className="drawer-content flex flex-col p-4">
                {/* Page content here */}
                <Outlet />
            </div>
            <div className="drawer-side">
                <ul className="menu p-4 m-0 w-60 min-h-full bg-base-200 text-base-content">
                    <li className="menu-title text-center">Docs</li>
                    {/* Sidebar content here */}
                    <li><a>Space 1</a></li>
                    <li><a>Kanban board</a></li>
                </ul>

            </div>
        </div>
    )
}
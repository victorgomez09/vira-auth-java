export default function DocsView() {
    return (
        <div className="flex flex-col">
            <h3>Bienvenido, Vira.</h3>
            <div className="mt-10">
                <h5>Estos son tus espacios:</h5>
                <div className="flex flex-wrap gap-2">
                    <div className="card shadow-sm bg-base-300 cursor-pointer hover:scale-105">
                        <div className="card-body">
                            <h3>Space 1</h3>
                        </div>
                    </div>

                    <div className="card shadow-sm bg-base-300 cursor-pointer hover:scale-105">
                        <div className="card-body">
                            <h3>Space 2</h3>
                        </div>
                    </div>
                </div>
            </div>

            <div className="mt-6">
                <h5>Estas son tus páginas:</h5>
                <div className="flex flex-wrap gap-2">
                    <div className="card shadow-sm bg-base-300 cursor-pointer hover:scale-105">
                        <div className="card-body">
                            <h3>Page 1</h3>
                        </div>
                    </div>

                    <div className="card shadow-sm bg-base-300 cursor-pointer hover:scale-105">
                        <div className="card-body">
                            <h3>Page 2</h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import Card from "../components/Card";
import { supabase } from "../client";

type Creator = {
    id: number;
    name: string;
    url: string;
    description: string;
    imageURL: string;
};

const ShowCreators: React.FC = () => {
    const [creators, setCreators] = useState<Creator[]>([]);

    useEffect(() => {
        const fetchCreators = async () => {
            const { data, error } = await supabase.from("creators").select("*");
            if (error) {
                console.error("Error fetching creators:", error);
            } else if (data) {
                setCreators(data as Creator[]);
            }
        };

        fetchCreators();
    }, []);

    return (
        <div className="container">
            <h1>Creatorverse</h1>


            <Link to="/new">
                <button>
                    Add New Creator
                </button>
            </Link>

            <div
                className="grid"
                style={{
                    display: "grid",
                    gridTemplateColumns: "repeat(auto-fill, minmax(250px, 1fr))",
                    gap: "2rem",
                    marginTop: 40,
                }}
            >
                {creators.length > 0 ? (
                    creators.map((creator) => (
                        <Card
                            id={creator.id}
                            name={creator.name}
                            url={creator.url}
                            description={creator.description}
                            imageURL={creator.imageURL}
                        />
                    ))
                ) : (
                    <p>No creators yet. Add one!</p>
                )}
            </div>
        </div>
    );
};

export default ShowCreators;

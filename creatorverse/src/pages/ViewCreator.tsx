import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { supabase } from "../client";
import { Link } from "react-router-dom";


const ViewCreator = () => {
    const { id } = useParams(); // grab id from URL
    const [creator, setCreator] = useState<any>(null);

    useEffect(() => {
        const fetchCreator = async () => {
            const { data, error } = await supabase
                .from("creators")
                .select("*")
                .eq("id", id) // filter by id
                .single(); // only one row

            if (error) {
                console.error("Error fetching creator:", error);
            } else {
                setCreator(data);
            }
        };

        if (id) fetchCreator();
    }, [id]);

    if (!creator) {
        return <p>Loading creator...</p>;
    }

    return (
        <div>
            <div>
                <h1>Creator's Profile</h1>

            </div>

            <img
                src={creator.imageURL}
                alt={creator.name}
            />
            <h1>{creator.name}</h1>
            <p>{creator.description}</p>


            <button onClick={() => window.open(creator.url)}

            >
                Visit Channel
            </button>
        </div>
    );
};

export default ViewCreator;

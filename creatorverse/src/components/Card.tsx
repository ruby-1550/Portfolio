import React from "react";
import { Link } from "react-router-dom";

interface CardProps {
    id: number;
    name: string;
    url: string;
    description: string;
    imageURL?: string; // optional
}

const Card: React.FC<CardProps> = ({ id, name, url, description, imageURL }) => {
    return (
        <div style={{ justifyContent: "left", }}
        >

            <div
                className="creator-card"
                style={{
                    backgroundImage: imageURL ? `url(${imageURL})` : undefined,
                    backgroundSize: "cover",
                    backgroundPosition: "center",
                    borderRadius: "1rem",
                    padding: "1rem",
                    minHeight: "200px",
                    display: "flex",
                    flexDirection: "column",
                    justifyContent: "flex-end",
                    color: "white",
                    boxShadow: "0 4px 8px rgba(0,0,0,0.2)",
                    position: "relative",
                }}
            >
                <a href={`/creator/${id}/edit`}
                    style={{
                        position: "absolute",
                        top: "10px",
                        right: "10px",
                        color: "white",
                        fontWeight: 500,
                        textDecoration: "underline",
                    }}>
                    Edit
                </a>

                <div
                    style={{
                        backgroundColor: "rgba(0,0,0,0.5)", // overlay for readability
                        borderRadius: "0.5rem",
                        padding: "0.5rem",


                    }}
                >
                    <h2 className="text-lg font-bold">{name}</h2>
                    <p className="text-sm">{description}</p>
                    <Link to={`/creator/${id}`}>
                        <button>
                            View
                        </button>
                    </Link>
                </div>
            </div>
        </div>
    );
};

export default Card;

import React, { FC } from "react";

import ReactStars from "react-rating-stars-component";

interface IRatingProps {
  value: number
  onChange: (newRating: number) => void;
}

const Rating: FC<IRatingProps> = ({value, onChange}) => {
  return (
    <ReactStars
    value={value}
    activeColor="#ffd700"
    count={5}
    size={14}
    isHalf={true}
    emptyIcon={<i className="far fa-star"></i>}
    halfIcon={<i className="fa fa-star-half-alt"></i>}
    fullIcon={<i className="fa fa-star"></i>}
    onChange={onChange}
    />
  );
};

export default Rating;

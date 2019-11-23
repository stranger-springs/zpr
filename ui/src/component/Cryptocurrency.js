import React from "react";

class Cryptocurrency extends React.Component {
  
  render() {
    const { name, abrreviation, image, desc } = this.props.details;
    
    return (
      <li className="cryptolist">
        <span className="logo">
            <img id ="image" src={image} alt={name}  width="50px" height="50px"/>
            <h2 id="abbr">{abrreviation}</h2>
        </span>
        <h3 className="crypto-name">
          {name}
        </h3>  
        <p className="desc">{desc}</p>
      </li>
    );
  }
}

export default Cryptocurrency;

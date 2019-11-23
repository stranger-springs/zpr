import React from 'react';

class Header extends React.Component {

    render() {
        return (
            <header className="top">
                <span className="cryptocurrency"> 
                    <span className="crypto">Crypto</span>
                    <span className="currency">currency</span>
                </span>
            </header>
        );
    }
}

export default Header;
import React from 'react';

class NavigationBar extends React.Component {

  render() {
    return(
        <div className="navigation_bar">
          <button>Historic Button</button>
          <button>Realtime Button</button>
        </div>
    );
  }
}

export default NavigationBar
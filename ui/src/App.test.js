import React from 'react';
import ReactDOM from 'react-dom';
import CurrencyPage from './component/pages/CurrencyPage';

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<CurrencyPage />, div);
  ReactDOM.unmountComponentAtNode(div);
});

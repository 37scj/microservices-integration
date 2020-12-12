import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import SuspenseApp from './Suspense';
import { Provider } from 'react-redux';
import store from './store';
import 'fontsource-roboto';

ReactDOM.render(
  (<React.Suspense fallback={SuspenseApp}>
    <Provider store={store}>
      <App />
    </Provider>
  </React.Suspense>),
  document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.register();

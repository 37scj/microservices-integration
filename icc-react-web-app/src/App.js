import React from 'react';
import './App.css';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  NavLink
} from "react-router-dom";
import { Fade } from '@material-ui/core';
import Home from './pages/home/home';
import Tracking from './pages/tracking/tracking';
import CssBaseline from '@material-ui/core/CssBaseline';

const routes = [
  { name: 'Home', path: '/', exact: true, component: Home },
  { name: 'Mapa', path: '/tracking', exact: true, component: Tracking },
];

function App() {
  return (
    <div>
      <CssBaseline />
      <header className="App-header">ICC - Inter Comm Central</header>
      <div className="App">
        <Router>
          <div className="menu">
            <ul className="nav">
              {routes.map((route, i) => (<li key={i} className="navItem">
                <NavLink to={route.path} exact={route.exact}>{route.name}</NavLink>
              </li>
              ))}
            </ul>
          </div>
          <div className="content">
            <Fade timeout={600} in={true} >
              <Switch >
                {routes.map((route, i) => (<Route
                  key={i}
                  path={route.path}
                  exact={route.exact}
                  component={route.component}
                />
                ))}
                <Route path='*' component={ComponenteDePagina404} />
              </Switch>
            </Fade>
          </div>
        </Router>
      </div>
      <footer>
        Copyright &copy;
      </footer>
    </div>
  );
}

function ComponenteDePagina404() {
  return (<div className="App-notFound">Page not found</div>);
}

export default App;

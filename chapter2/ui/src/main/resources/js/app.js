var Router         = ReactRouter;
var RouteHandler  = Router.RouteHandler;
var DefaultRoute     = ReactRouter.DefaultRoute;
var Route          = Router.Route;

var App = React.createClass({
  render: function() {
    return (
      <div>
        <RouteHandler/>
      </div>
    );
  }
});

var Home = React.createClass({
  render: function() {
    return (
      <div id="Welcome">
        <p>
        Employees Application
        </p>
      </div>
    );
  }
});

var routes = (
  <Route path="/" handler={App}>
    <DefaultRoute name="home" handler={Home}/>
  </Route>
);

Router.run(routes, Router.HistoryLocation, function (Handler) {
  ReactDOM.render(<Handler/>, document.getElementById('app'));
});

var Router         = ReactRouter;
var RouteHandler   = Router.RouteHandler;
var DefaultRoute   = ReactRouter.DefaultRoute;
var Route          = Router.Route;
var Link           = Router.Link;

var App = React.createClass({
  render: function() {
    return (
      <div>
        <LeftNav/>
        <div className="container-fluid container-cards-pf container-pf-nav-pf-vertical container-pf-nav-pf-vertical-with-secondary ">
          <div className="row row-cards-pf">
            <RouteHandler/>
          </div>
        </div>
      </div>
    );
  }
});

var LeftNav = React.createClass({
  render: function() {
    return (
      <div className="nav-pf-vertical nav-pf-vertical-with-secondary-nav ">
        <ul className="list-group">
          <li className="list-group-item active">
            <Link to="employees">
              <span className="pficon pficon-users"></span>
              <span className="list-group-item-value">Employees</span>
            </Link>
          </li>
          <li className="list-group-item">
            <Link to="addresses">
              <span className="fa fa-book"></span>
              <span className="list-group-item-value">Addresses</span>
            </Link>
          </li>
        </ul>
      </div>
    );
  }
});

var Home = React.createClass({
  render: function() {
    return (
      <Employees/>
    );
  }
});

var Employees = React.createClass({
  render: function() {
    return (
      <h1>Employees</h1>
    );
  }
});

var Employee = React.createClass({
  render: function() {
    return (
      <h1>Single Employee</h1>
    );
  }
});

var Addresses = React.createClass({
  render: function() {
    return (
      <h1>Addresses</h1>
    );
  }
});

var Address = React.createClass({
  render: function() {
    return (
      <h1>Single Address</h1>
    );
  }
});

var routes = (
  <Route path="/" handler={App}>
    <DefaultRoute name="home" handler={Home}/>
    <Route name="employees">
      <Route name="employee" path=":id" handler={Employee}/>
      <DefaultRoute handler={Employees}/>
    </Route>
    <Route name="addresses">
      <Route name="address" path=":id" handler={Address}/>
      <DefaultRoute handler={Addresses}/>
    </Route>
  </Route>
);

Router.run(routes, Router.HistoryLocation, function (Handler) {
  ReactDOM.render(<Handler/>, document.getElementById('app'));
});

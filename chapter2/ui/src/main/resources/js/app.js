var Router         = ReactRouter;
var RouteHandler   = Router.RouteHandler;
var DefaultRoute   = ReactRouter.DefaultRoute;
var Route          = Router.Route;
var Link           = Router.Link;

var Chapter2 = {};
Chapter2.State = {};
Chapter2.Actions = {};

Chapter2.App = React.createClass({
  render: function() {
    return (
      <div>
        <Chapter2.LeftNav/>
        <div className="container-fluid container-cards-pf container-pf-nav-pf-vertical container-pf-nav-pf-vertical-with-secondary ">
          <div className="row row-cards-pf">
            <RouteHandler/>
          </div>
        </div>
      </div>
    );
  }
});

Chapter2.LeftNav = React.createClass({
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

Chapter2.Home = React.createClass({
  render: function() {
    return (
      <Chapter2.Employees/>
    );
  }
});

Chapter2.Actions.Employees = Reflux.createActions({
  "load": {children: ["completed","failed"]}
});

Chapter2.Actions.Employees.load.listen( function() {
  topo.ajax("chapter2", "/employee")
    .then(function(data) {
      Chapter2.Actions.Employees.load.completed(data);
    }, function(err) {
      console.log("Retrieving employees failed", err);
      Chapter2.Actions.Employees.load.failed;
    });
});

Chapter2.State.Employees = Reflux.createStore({
  init: function() {
    this.listenTo(Chapter2.Actions.Employees.load.completed, this.output);
  },

  output: function(results) {
    this.trigger(results);
  }
});

Chapter2.Employees = React.createClass({
  mixins: [Reflux.connect(Chapter2.State.Employees,"items")],

  componentWillMount: function() {
    Chapter2.Actions.Employees.load()
  },

  getInitialState: function() {
    return {
      items: [],
    }
  },

  componentDidMount: function() {
    $(document).ready(function () {
      var widest = 0;
      $('.list-view-pf-equalized-column .list-view-pf-additional-info-item').each( function() {
        widest = $(this).width() > widest ? $(this).width() : widest;
      }).width(widest);
    });
  },

  render: function() {
    return (
      <div>
        <h1>Employees</h1>
        <div className="list-group list-view-pf">
          {
            this.state.items.map(function(e) {
              return (
                <div className="list-group-item list-view-pf-stacked" key={e.id}>
                  <div className="list-view-pf-actions">
                    <button className="btn btn-info">Details</button>
                    <button className="btn btn-danger">Delete</button>
                  </div>
                  <div className="list-view-pf-main-info">
                    <div className="list-view-pf-left">
                      <span className="pficon pficon-user list-view-pf-icon-md list-view-pf-icon-success"></span>
                    </div>
                    <div className="list-view-pf-body">
                      <div className="list-view-pf-description">
                        <div className="list-group-item-heading">
                          {e.firstname} {e.lastname}
                        </div>
                        <div className="list-group-item-text" data-toggle="tooltip" title="Social Security Number">
                          <span>SSN:</span> {e.socialSecurity}
                        </div>
                      </div>
                      <div className="list-view-pf-additional-info">
                        <div className="list-view-pf-additional-info-item">
                          <span>Home Ph:&nbsp;</span> {e.homePhone}
                        </div>
                        <div className="list-view-pf-additional-info-item">
                          <span>Salary:&nbsp;</span> {e.salary}
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              );
            })
          }
        </div>
      </div>
    );
  }
});

Chapter2.EmployeeDetail = React.createClass({
  render: function() {
    return (
      <h1>Single Employee</h1>
    );
  }
});

Chapter2.Addresses = React.createClass({
  render: function() {
    return (
      <h1>Addresses</h1>
    );
  }
});

Chapter2.Address = React.createClass({
  render: function() {
    return (
      <h1>Single Address</h1>
    );
  }
});

var routes = (
  <Route path="/" handler={Chapter2.App}>
    <DefaultRoute name="home" handler={Chapter2.Home}/>
    <Route name="employees">
      <Route name="employee-detail" path=":id" handler={Chapter2.EmployeeDetail}/>
      <DefaultRoute handler={Chapter2.Employees}/>
    </Route>
    <Route name="addresses">
      <Route name="address" path=":id" handler={Chapter2.Address}/>
      <DefaultRoute handler={Chapter2.Addresses}/>
    </Route>
  </Route>
);

Router.run(routes, Router.HistoryLocation, function (Handler) {
  ReactDOM.render(<Handler/>, document.getElementById('app'));
});

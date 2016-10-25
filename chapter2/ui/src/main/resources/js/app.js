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
            <Link to="addresses">
              <span className="fa fa-envelope"></span>
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
      <Chapter2.Addresses/>
    );
  }
});

Chapter2.Actions.Addresses = Reflux.createActions({
  "load": {children: ["completed","failed"]}
});

Chapter2.Actions.Addresses.load.listen( function() {
  $.get("http://localhost:8081/address")
    .done((data) => {
      Chapter2.Actions.Addresses.load.completed(data);
    })
    .fail((err) => {
      console.log("Retrieving addresses failed", err);
      Chapter2.Actions.Addresses.load.failed;
    });
});

Chapter2.State.Addresses = Reflux.createStore({
  init: function() {
    this.listenTo(Chapter2.Actions.Addresses.load.completed, this.output);
  },

  output: function(results) {
    this.trigger(results);
  }
});

Chapter2.Addresses = React.createClass({
  mixins: [Reflux.connect(Chapter2.State.Addresses,"items")],

  componentWillMount: function() {
    Chapter2.Actions.Addresses.load()
  },

  getInitialState: function() {
    return {
      items: [],
    }
  },

  render: function() {
    return (
      <div>
        <h1>Addresses</h1>
        <table id="addressTable" className="datatable table table-striped table-bordered">
          <thead>
            <tr>
              <th>First Line</th>
              <th>Second Line</th>
              <th>City</th>
              <th>State</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
          {
            this.state.items.map(function(a) {
              return (
                <tr key={a.id}>
                  <td>{a.firstLine}</td>
                  <td>{a.secondLine}</td>
                  <td>{a.city}</td>
                  <td>{a.state}</td>
                  <td>
                    <div className="list-view-pf-actions">
                      <button className="btn btn-info">Details</button>
                      <a className="btn btn-danger" onClick="{() => {if(confirm('Delete the address?')} {this.deleteItem};}}">Delete</a>
                    </div>
                  </td>
                </tr>
              );
            })
          }
          </tbody>
        </table>
      </div>
    );
  }
});

Chapter2.AddressDetail = React.createClass({
  render: function() {
    return (
      <h1>Address</h1>
    );
  }
});

var Modal = React.createClass({
  componentDidMount() {
    $(this.getDOMNode()).modal('show');
    $(this.getDOMNode()).on('hidden.bs.modal', this.props.handleHideModal);
  },

  render() {
    return (
        <div className="modal fade">
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <button type="button" className="close" data-dismiss="modal" aria-label="Close" aria-hidden="true">
                  <span class="pficon pficon-close"></span>
                </button>
                <h4 className="modal-title">Delete Address</h4>
              </div>
              <div className="modal-body">
                <p>Please confirm you wish to delete the address.</p>
              </div>
              <div className="modal-footer">
                <button type="button" className="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" className="btn btn-primary">Delete</button>
              </div>
            </div>
          </div>
        </div>
      )
  },

  propTypes:{
    handleHideModal: React.PropTypes.func.isRequired
  }
});

//Chapter2.AddressDelete = React.createClass({
//  render: function() {
//    return (
//      <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
//        <div class="modal-dialog">
//          <div class="modal-content">
//            <div class="modal-header">
//              <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
//                <span class="pficon pficon-close"></span>
//              </button>
//              <h4 class="modal-title" id="deleteModalLabel">Delete</h4>
//            </div>
//            <div class="modal-body">
//              <form class="form-horizontal">
//                <div class="form-group">
//                  <label class="col-sm-3 control-label" for="textInput-modal-markup">Field One</label>
//                  <div class="col-sm-9">
//                    <input type="text" id="textInput-modal-markup" class="form-control"></div>
//                </div>
//                <div class="form-group">
//                  <label class="col-sm-3 control-label" for="textInput2-modal-markup">Field Two</label>
//                  <div class="col-sm-9">
//                    <input type="text" id="textInput2-modal-markup" class="form-control"></div>
//                </div>
//                <div class="form-group">
//                  <label class="col-sm-3 control-label" for="textInput3-modal-markup">Field Three</label>
//                  <div class="col-sm-9">
//                    <input type="text" id="textInput3-modal-markup" class="form-control">
//                  </div>
//                </div>
//              </form>
//            </div>
//            <div class="modal-footer">
//              <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
//              <button type="button" class="btn btn-primary">Save</button>
//            </div>
//          </div>
//        </div>
//      </div>
//    );
//  }
//});

var routes = (
  <Route path="/" handler={Chapter2.App}>
    <DefaultRoute name="home" handler={Chapter2.Home}/>
    <Route name="addresses">
      <Route name="address-detail" path=":id" handler={Chapter2.AddressDetail}/>
      <DefaultRoute handler={Chapter2.Addresses}/>
    </Route>
  </Route>
);

Router.run(routes, Router.HistoryLocation, function (Handler) {
  ReactDOM.render(<Handler/>, document.getElementById('app'));
});

var path = require('path');
var webpack = require('webpack');

module.exports = {
  devtool: 'source-map',

  entry: './app/index.js',

  output: {
    path: path.join(__dirname, 'target/chapter2-ui'),
    filename: 'bundle.js',
    publicPath: '/'
  },

  module: {
    loaders: [
      {
        test: /\.js$/,
        exclude: /node_modules/,
        loaders: ['babel-loader']
      }
    ]
  },

  plugins: [
    new webpack.optimize.OccurrenceOrderPlugin(),

    new webpack.DefinePlugin({
      'process.env': {
        'NODE_ENV': JSON.stringify('production')
      }
    }),

    new webpack.optimize.UglifyJsPlugin({
      compressor: {
        warnings: false
      }
    })
  ]
};

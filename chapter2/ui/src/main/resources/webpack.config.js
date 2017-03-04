var path = require('path');
var webpack = require('webpack');

module.exports = {
  devtool: 'eval-source-map',

  entry: [
    'babel-polyfill',
    './app/index.js'
  ],

  output: {
    path: path.resolve(__dirname, "build"),
    filename: 'bundle.js',
    publicPath: '/'
  },

  resolve: {
    extensions: ['.js', '.jsx']
  },

  module: {
    loaders: [
      {
        test: /\.js$/,
        exclude: /node_modules/,
        loader: 'babel-loader'
      }
    ]
  }
}

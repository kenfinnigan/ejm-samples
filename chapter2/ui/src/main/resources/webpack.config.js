var path = require('path');
var webpack = require('webpack');

module.exports = {
  devtool: 'eval-source-map',

  entry: [
    'babel-polyfill',
    './index.js'
  ],

  devServer: {
    inline: true,
    port: 3333
  },

  output: {
    filename: 'bundle.js'
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

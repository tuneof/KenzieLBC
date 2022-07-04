const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyPlugin = require("copy-webpack-plugin");
const { CleanWebpackPlugin } = require('clean-webpack-plugin');


module.exports = {
  optimization: {
    usedExports: true
  },
  entry: {
    restaurantPage: path.resolve(__dirname, 'src', 'pages', 'restaurantPage.js'),
    restaurantPage: path.resolve(__dirname, 'src', 'pages', 'reviewPage.js'),
},
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: '[name].js',
  },
  devServer: {
    https: true,
    port: 8080,
    open: true,
    openPage: 'https://localhost:8080',
    // diableHostChecks, otherwise we get an error about headers and the page won't render
    disableHostCheck: true,
    contentBase: 'packaging_additional_published_artifacts',
    // overlay shows a full-screen overlay in the browser when there are compiler errors or warnings
    overlay: true,
    proxy: [
        {
           context: [
             '/restaurants'
             '/reviews'

           ],
           target: 'http://localhost:5001'
        }
    ]
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: './src/index.html',
      filename: 'index.html',
      inject: false
    }),
        new HtmlWebpackPlugin({
          template: './src/index.html',
          filename: 'review.html',
          inject: false
        }),
    new CopyPlugin({
      patterns: [
        {
          from: path.resolve('src/css'),
          to: path.resolve("dist/css")
        }
      ]
    }),
    new CleanWebpackPlugin()   
  ]
}

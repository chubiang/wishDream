const webpack = require('webpack');
const path = require('path');
const Fiber = require('fibers');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const UglifyJsPlugin = require('uglifyjs-webpack-plugin');

module.exports = {
	// production 모드로 하면 웹팩4는 기본으로 optimization & minification 해줌
    mode: 'none',
    cache: false,
    context: path.resolve(__dirname, 'public'),
    entry: {
        app: './app.js'
    },
    devtool: 'sourcemaps',
    output: {
        path: path.resolve(__dirname, 'src/main/webapp/js'),
        filename: '[name].bundle.js'
    },
    module: {
        eslint: {
          configFile: './.eslintrc',
          emitWarning: true
        },
        rules: [
            {
              enforce: 'pre',
              test: /\.(js|jsx)$/,
              exclude: /node_modules/,
              use: [
                { loader: "eslint-loader" },
                { loader: "babel-loader" }
              ]
            },
           {
             test: /\.scss$/,
             use: [ { loader: 'style-loader' },
                    { loader: 'css-loader'   },
                    {
                        loader: 'sass-loader',
                        options: {
                            implementation: require("sass"),
                            fiber: Fiber
                        }
                    }
                 ]
             }
        ]
    },
    optimization: {
        minimizer: [
        	new UglifyJsPlugin({
	    			uglifyOptions: {
	    				compress:false,
	    				ecma: 6,
	    				mangle: true
	    			},
	    			exclude: /\/node_modules/,
	    			cache: true,
        			parallel: true,
        			sourceMap: true
        	})
        ],
    },
    plugins: [
    	new HtmlWebpackPlugin({
	    	template: 'public/index.html',
	    	inject: true,
	    	sourceMep: true
    })]
};

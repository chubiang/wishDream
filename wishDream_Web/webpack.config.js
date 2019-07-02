// const webpack = require('webpack');
const path = require('path');
const Fiber = require('fibers');
const sass = require("sass");
const PolyfillInjectorPlugin = require('webpack-polyfill-injector');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const UglifyJsPlugin = require('uglifyjs-webpack-plugin');

module.exports = {
	// production 모드로 하면 웹팩4는 기본으로 optimization & minification 해줌
    mode: 'none',
    cache: false,
    context: path.resolve(__dirname, 'public'),
    entry: {
        /*
        polyfills: `webpack-polyfill-injector?$(JSON.stringify({
            modules: [
                './services/polyfills.js'
            ]
        })!`,
        */
        app: './app.js',
        
        
    },
    devtool: 'sourcemaps',
    output: {
        path: path.resolve(__dirname, 'src/main/webapp/js'),
        filename: '[name].bundle.js'
    },
    plugins: [
    	new HtmlWebpackPlugin({
            template: 'index.html',
            filename: 'index.html',
	    	inject: true,
	    	sourceMep: true
        }),
        /*
        new PolyfillInjectorPlugin({
            polyfills: [
                'Promise',
                'Array.prototype.find',
                'Array.prototype.flat',
                'Element.prototype.closest'
            ]
        })
        */
    ],
    module: {
        rules: [
            {
              enforce: 'pre',
              test: /\.(js|jsx)$/,
              exclude: /node_modules/,
              use: (info) => ([
                  {
                    loader: "babel-loader",
                    options: {
                        presets: [
                            // TODO: browserslist 적용해야됨.
                            '@babel/preset-env', {
                                targets: { 
                                    node: 'current', 
                                    chrome: "58",
                                    ie: "11" 
                                },
                                modules: 'false'
                        }]
                    }
                    }
                ]),
            },
           {
                test: /\.scss$/,
                use: [{
                    loader: 'sass-loader',
                    options: {
                        implementation: sass,
                        fiber: Fiber
                    }
                }]
            },
            {   // TODO
                test: /\.css$/i,
                loader: 'css-loader',
                options: {
                    url: (url, resourcePath) => {
                        if (url.includes('.png')) return false;
                        return true;
                    },
                    sourceMap: true,
                    import: true,
                }
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
    }
};

// const webpack = require('webpack');
const path = require('path');
const Fiber = require('fibers');
const sass = require("sass");
const PolyfillInjectorPlugin = require('webpack-polyfill-injector');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const UglifyJsPlugin = require('uglifyjs-webpack-plugin');
const MiniCssExtractPlugin = require("mini-css-extract-plugin");

const devMode = process.env.NODE_ENV !== 'production';

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
            title: 'wishDream',
            minify: {
                collapseWhitespace: true,
                removeComments: true,
                removeRedundantAttributes: true,
                removeScriptTypeAttributes: true,
                removeStyleLinkTypeAttributes: true,
                useShortDoctype: true
            },
        }),
        new MiniCssExtractPlugin({
            filename: devMode ? '[name].css' : '[name].[hash].css',
            chunkFilename: devMode ? '[id].css' : '[id].[hash].css',
        })
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
                            ['@babel/preset-env', {
                                targets: { 
                                    browsers: "> 0.2%"  
                                },
                                modules: 'false',
                                useBuiltIns: 'entry'
                            }],
                            '@babel/preset-react',
                            '@babel/plugin-transform-runtime'
                        ]}
                    }
                ]),
            },
            {  
                test: /\.(sa|sc|c)ss$/,
                use: [
                    {
                        loader: 'css-loader',
                        options: {
                            url: (url, resourcePath) => {
                                if (url.includes('.png')) return false;
                                return true;
                            },
                            minimize: true,
                            sourceMap: true,
                        }
                    }, 
                    {
                        loader: 'postcss-loader'
                    }, 
                    {
                        loader: 'sass-loader',
                        options: {
                            implementation: sass,
                            fiber: Fiber
                        }
                    },
                    {   
                        loader: MiniCssExtractPlugin.loader,
                        options: {
                            publicPath: '/styles/',
                            hmr: process.env.NODE_ENV === 'development',
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
        			sourceMap: true
        	})
        ],
    }
};

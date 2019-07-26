// const webpack = require('webpack');
const path = require('path');
const Fiber = require('fibers');
const sass = require("node-sass");
const PolyfillInjectorPlugin = require('webpack-polyfill-injector');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const UglifyJsPlugin = require('uglifyjs-webpack-plugin');
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const MediaQueryPlugin = require('media-query-plugin');
const CleanWebpackPlugin   = require('clean-webpack-plugin').CleanWebpackPlugin;
const CopyWebpackPlugin = require('copy-webpack-plugin');
const ExtractTextPlugin = require("extract-text-webpack-plugin");

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
    module: {
        rules: [
            {
              enforce: 'pre',
              test: /\.(js|jsx)$/,
              exclude: /(node_modules)/,
              use: (info) => ([
                  {
                    loader: "babel-loader",
                    /*
                    options: {
                        presets: [
                            ['@babel/preset-env', {
                                targets: {
                                    browsers: "> 0.2%"
                                },
                                modules: 'false',
                                useBuiltIns: 'entry'
                            }],
                            '@babel/preset-react'
                        ]}
                    */
                  },
                  { loader: 'astroturf/loader'}
                ]),
            },
            {
              test: /\.(sa|sc|c)ss$/,
              exclude: /fonts/,
              use: ExtractTextPlugin.extract({
                fallback: 'style-loader',
                use: [
                  {
                    loader: MiniCssExtractPlugin.loader,
                    options: {
                      publicPath: 'styles',
                      sourceMap: true
                    }
                  },
                  {
                    loader: 'sass-loader',
                    options: {
                        implementation: sass,
                        includePaths: [path.resolve(__dirname, '/node_modules'), path.resolve(__dirname, '/styles')],
                        fiber: Fiber,
                        sourceMap: true
                    }
                  },
                  {
                    loader: 'css-loader',
                    options: {
                        sourceMap: true,
                        importLoaders: 2,
                    }
                  },
                  {
                    loader: 'postcss-loader',
                    options: {
                      sourceMap: true
                    }
                  },
                  {
                    loader: MediaQueryPlugin.loader,
                  },
                ]
              })
            },
           {
    				test: /\.(eot|ttf|woff2?|otf)$/,
    				use: 'url-loader?limit=1024&name=fonts/[name].[ext]'
  			   },{
    				test: /\.(jpe?g|png|gif|svg)$/,
    				include: /public/,
    				use: 'url-loader?limit=5000&name=assets/img/[name].[ext]'
  			   },{
    				test: /\.(mp4)$/,
    				use: 'url-loader?limit=500000&name=assets/video/[name].[ext]'
  			   }
        ]
    },
    plugins: [
       new CleanWebpackPlugin(),
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
            ignoreOrder: true,
        }),
        new MediaQueryPlugin({
            include: [
                //'exampleFile'
            ],
            queries: {
                'print, screen and (min-width: 75em)': 'desktop'
            }
        }),
        new CopyWebpackPlugin([
          {
            from: 'images', to: '../images'
          }
        ]),
        new ExtractTextPlugin("[name].css"),
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
    optimization: {
        minimizer: [
        	new UglifyJsPlugin({
	    			uglifyOptions: {
	    				compress:false,
	    				ecma: 6,
	    				mangle: true
	    			},
	    			exclude: /(\/node_modules)/,
	    			cache: true,
        			sourceMap: true
        	})
        ],
    }
};

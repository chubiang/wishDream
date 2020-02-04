// const webpack = require('webpack');
const path = require('path');
const webpack = require('webpack');
const Fiber = require('fibers');
const sass = require("sass");
const websocket = require("websocket");
const HtmlWebpackPlugin = require('html-webpack-plugin');
const UglifyJsPlugin = require('uglifyjs-webpack-plugin');
const OptimizeCSSAssetsPlugin = require('optimize-css-assets-webpack-plugin');
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const MediaQueryPlugin = require('media-query-plugin');
const CleanWebpackPlugin   = require('clean-webpack-plugin').CleanWebpackPlugin;
const CopyWebpackPlugin = require('copy-webpack-plugin');
const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin;
const DirectoryNamedWebpackPlugin = require("directory-named-webpack-plugin");//디렉토리 이름과 같은 파일 연결

const devMode = process.env.NODE_ENV !== 'production';

module.exports = {
	// production 모드로 하면 웹팩4는 기본으로 optimization & minification 해줌
    mode: 'none',
    cache: false,
    context: path.resolve(__dirname, 'public'),
    entry: {
        index: './index.js',
        login: './login.js',
    },
    devtool: 'sourcemaps',
    output: {
        path: path.resolve(__dirname, 'src/main/resources/static'),
        publicPath:'/static',
        filename: '[name].bundle.js'
    },
    node: {
      fs: 'empty'
    },
    resolve: {
      modules: [ path.resolve(__dirname, 'public'), 'node_modules' ],
      plugins: [ new DirectoryNamedWebpackPlugin() ],
      alias: {
        '@material-ui/styles': path.resolve(__dirname, 'node_modules', '@material-ui/styles'),
      }
    },
    externals: {
      websocket: websocket
    },
    module: {
        rules: [
            {
              enforce: 'pre',
              test: /\.(js|jsx)$/,
              exclude: /(node_modules)|(static)/,
              use: (info) => ([
                { loader: "babel-loader" },
              ]),
            },
            {
              test: /\.(sa|sc|c)ss$/,
              exclude:/(node_modules)|(static)/,
              use: [
                {
                  loader: MiniCssExtractPlugin.loader,
                  options: {
                    publicPath: (resourcePath, context) => {
                      // publicPath is the relative path of the resource to the context
                      // e.g. for ./css/admin/main.css the publicPath will be ../../
                      // while for ./css/main.css the publicPath will be ../
                      return path.relative(path.dirname(resourcePath), context) + '/';
                    },
                    hmr: process.env.NODE_ENV === 'development',
                  }
                },
                {
                  loader: 'css-loader',
                  options: {
                    importLoaders: 1,
                    sourceMap: true
                  }
                },
                {
                  loader: 'sass-loader',
                  options: {
                      implementation: sass,
                      includePaths: [path.resolve(__dirname, './public/styles')],
                      fiber: Fiber,
                      sourceMap: true
                  }
                },
                { 
                  loader: 'postcss-loader', 
                  options: { 
                    ident: "embedded",
                    config: {
                      path: __dirname + '/postcss.config.js',
                      ctx: {
                        parser: 'sugarss',
                        env: 'development',
                        map: true
                      }
                    },
                    ident: 'postcss',
                    plugins: (loader) => [
                      require('postcss-import')({ root: loader.resourcePath }),
                      require('postcss-preset-env')(),
                      require('cssnano')()
                    ]
                  },
                },
                {
                  loader: 'resolve-url-loader',
                  options: {
                      engine: 'postcss',
                      sourceMap: true,
                  }
                }
              ]
            },
            {
              test: /\.(eot|ttf|woff|woff2?|otf)$/,
    				  use: 'url-loader?limit=100000&name=fonts/[name].[ext]'
  			    },{
    				  test: /\.(jpe?g|png|gif|svg)$/,
    				  include: /public/,
    				  use: 'url-loader?limit=100000&name=assets/img/[name].[ext]'
  			    },{
    				  test: /\.(mp4)$/,
    				  use: 'url-loader?limit=500000&name=assets/video/[name].[ext]'
  			    }
        ]
    },
    plugins: [
       new webpack.ProgressPlugin(),
       new BundleAnalyzerPlugin(),
       new CleanWebpackPlugin({
         dry: true,
         cleanOnceBeforeBuildPatterns: ['**/*', 'index.html', 'login.html']
       }),
    	 new HtmlWebpackPlugin({
            title: 'wishDream',
            inject: 'body',
            chunks: [ 'index' ],
            hash: true,
            filename : 'index.html',
            template: './index.html',
            xhtml: true,
            minify: {
              collapseWhitespace: true,
              removeComments: true,
              removeRedundantAttributes: true,
              removeScriptTypeAttributes: true,
              removeStyleLinkTypeAttributes: true,
              useShortDoctype: true
            },
        }),
        new HtmlWebpackPlugin({
            title: 'wishDream',
            inject: 'body',
            chunks: [ 'login' ],
            hash: true,
            filename : 'login.html',
            template: './login.html',
            xhtml: true,
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
            filename: devMode ? 'css/[name].css' : 'css/[name].[hash].css',
            chunkFilename: devMode ? 'css/[id].css' : 'css/[id].[hash].css',
            ignoreOrder: true,
        }),
        new CopyWebpackPlugin([
          { from: 'images', to: '../images' },
          { from: 'json', to: '../json' },
          { from: 'icon', to:'./' }
        ]),
        //new BundleAnalyzerPlugin()
    ],
    optimization: {
        minimizer: [// dev겸 연습겸
        	new UglifyJsPlugin({
	    			uglifyOptions: {
	    				compress:false,
	    				ecma: 5,
	    				mangle: true
	    			},
	    			exclude: /(\/node_modules)/,
	    			// cache: true,
        		sourceMap: true
        	}),
          new OptimizeCSSAssetsPlugin({})
        ],
        splitChunks: {
          cacheGroups: {
            node_vendors: {
                test: /[\\/]node_modules[\\/]/,
                chunks: 'async',
                priority: 1
            },
            scss_vendors: {
                name: 'styles',
                test:/\.(sass|scss)$/,
                chunks: 'all',
                enforce: true,
            },
            css_vendors: {
                name: 'styles',
                test:/\.(css)$/,
                chunks: 'all',
                enforce: true,
            }
          }
        }
    }
};

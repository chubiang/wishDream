module.exports = {
  parser: "babel-eslint",
  env: {
    "browser": true,
    "node": true,
    "es6":  true,
    "jquery": true,
    "mocha": true
  },
  extends: ["airbnb","prettier"],
  parserOptions: {
    "ecmaVersion": 6,
    "sourceType": "module",
    "ecmaFeatures": {
        "jsx": true
    }
  },
  plugins: [
    "react",
    "react-hooks"
  ],
  rules: {
    "camelcase": 0,
    "vars-on-top": 0,
    "no-var": 0,
    "no-multi-assign": 0,
    "spaced-comment": 0,
    "no-underscore-dangle": 0,
    "func-names": 0,
    "no-return-assign": 0,
    "no-unused-vars": 0,
    "no-else-return": 1,
    "semi": [1, "always"],
    "use-isnan": 1,
    "no-unreachable": 1,
    "space-unary-ops": 2,
    "valid-typeof": 2,
    "no-extra-semi": 2,
    "no-self-assign": 2,
    "no-await-in-loop": 2,
    "no-compare-neg-zero": 2,
    "no-self-compare": 2,
    "no-prototype-builtins": 2,
    "react/prefer-stateless-function": 0,
    "react/jsx-filename-extension": 0,
    "react/jsx-one-expression-per-line": 0,
    "react-hooks/rules-of-hooks": "error",
    "react-hooks/exhaustive-deps": "warn"
  },
  overrides: [
    {
      files: ["*-test.js"],
      rules: {
        "no-unused-expressions": "off"
      }
    }
  ],
  globals: {
    "jQuery": true,
    "$": true
  }
}

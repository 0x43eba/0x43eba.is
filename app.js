const express = require('express');
const exphbs = require('express-handlebars');
const path = require('path');

const site = require('./content/site');

const app = express();

app.engine(
  'handlebars',
  exphbs.engine({
    helpers: {
      // 0 -> "01", used for the numbered focus grid
      ordinal: (index) => String(index + 1).padStart(2, '0'),
    },
  })
);
app.set('view engine', 'handlebars');
app.set('views', path.join(__dirname, 'views'));

app.use(
  express.static(path.join(__dirname, 'public'), {
    maxAge: process.env.NODE_ENV === 'production' ? '7d' : 0,
  })
);

app.get('/', (req, res) => {
  res.render('home', { site });
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Server listening on port ${PORT}`);
});

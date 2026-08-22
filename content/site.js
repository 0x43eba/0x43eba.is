/* ------------------------------------------------------------------
   site.js — everything you'd want to edit lives here.
   Nothing in views/ or public/css needs touching to change the words.

   HTML is allowed in `lead`, `aside`, `body[]` and `focus.items[].body`.
   Useful inline tags:
     <em>…</em>        italic, tinted with the accent colour
     <strong>…</strong> full-contrast ink
     <a href="…">…</a>  underlined link
   Everything else (name, role, labels, link text) is plain text and is
   escaped, so quotes and ampersands are safe to type literally.
   ------------------------------------------------------------------ */

module.exports = {
  /* ---- masthead ------------------------------------------------- */

  name: 'Russell Gill',

  // Rendered as a single line, separated by a tinted double slash.
  role: ['Performance Engineering', 'Artificial Intelligence', 'Creative Technology', 'Reykjavík, Iceland'],

  avatar: {
    src: '/images/avatar.gif',
    alt: 'A green wireframe skull overgrown with foliage',
  },

  /* ---- the editorial body --------------------------------------- */

  // One large serif sentence. Keep it to roughly 15–25 words — it is set
  // at display size and is the first thing anyone reads.
  lead: "",

  // A short line directly under the lead. Set to null to remove it.
  aside: 'Computer programmer, mostly.',

  // The main prose. Each string is its own paragraph; add or remove
  // freely, or set this to [] to drop the section entirely.
  body: [
    "It's far easier to explain what a computer programmer does, and most of what I do ends or begins in computer programming. Besides, on paper my job is to write high-performance analytics software.",

    "That said, computer programming is just a means to an end for me. My primary interest is in how we use technology to extend our capacity. Understanding how technology can reduce day-to-day cognitive load and information fragmentation is my focus at the moment.",

    "In terms of education, I hold a General Bachelor of Science. My course trajectory did not cleanly fit into any specific major; physical chemistry, ethical philosophy, and phenomenology are tricky to combine in a single box. I dug deeply into what interested me at the time — a pattern that I follow today.",
  ],

  /* ---- focus grid ------------------------------------------------ */

  // Set `focus: null` to remove this whole section.
  focus: {
    label: "Questions I've Been Asked",
    items: [
      {
        title: 'Resilient System Design',
        body: 'How do you make sure an agentic system understands existing business processes and development processes?',
      },
      {
        title: 'High-Performance Systems',
        body: 'How do we store real-time orderbook data on a cryptocurrency exchange?',
      },
      {
        title: 'Physical Science',
        body: 'Is it possible to stabilize biomaterial so it can be used as paint?',
      },
      {
        title: '3D Design',
        body: 'How can we get a 3D capture of this audio installation?',
      },
    ],
  },

  /* ---- actions --------------------------------------------------- */

  email: {
    label: 'Email',
    address: 'russell@living-systems.is',
  },

  // Shown as text links beside the email button. `external: true` adds
  // the ↗ mark and opens in a new tab.
  links: [
    { label: 'GitHub', href: 'https://github.com/0x43eba', external: true },
  ],

  /* ---- colophon (the small mono line at the bottom) --------------- */

  colophon: {
    items: ['0x43eba', '64°08′N 21°56′W'],

    // Appends a live local time to the colophon. Set to null to remove.
    clock: {
      timeZone: 'Atlantic/Reykjavik',
      label: 'Reykjavík',
    },
  },

  /* ---- <head> metadata -------------------------------------------- */

  meta: {
    title: 'Russell Gill',
    description:
      'Russell Gill — backend and systems developer in Reykjavík, Iceland. Systems architecture, high-performance backends, infrastructure, and integrations.',
    author: 'Russell Gill',
    keywords:
      'backend developer, systems architecture, infrastructure, integrations, high performance, software engineering, Reykjavík, Iceland',
    url: 'https://0x43eba.is',
    // Social preview card. 1200×630. Regenerate with: just og
    image: '/images/og.png',
    themeColor: '#131315',
  },
};

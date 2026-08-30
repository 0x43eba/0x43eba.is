(ns site)

(def config
  {:name "Russell Gill"
   :role ["Performance Engineering" "Artificial Intelligence" "Creative Technology" "Reykjavík, Iceland"]

   :avatar {:src "/images/avatar.gif"
            :alt "A green wireframe skull overgrown with foliage"}

   :lead ""
   :aside "Computer programmer, mostly."
   :body
   ["It's far easier to explain what a computer programmer does, and most of what I do ends or begins in computer programming. Besides, on paper my job is to write high-performance analytics software."

    "That said, computer programming is just a means to an end for me. My primary interest is in how we use technology to extend our capacity. Understanding how technology can reduce day-to-day cognitive load and information fragmentation is my focus at the moment."

    "In terms of education, I hold a General Bachelor of Science. My course trajectory did not cleanly fit into any specific major; physical chemistry, ethical philosophy, and phenomenology are tricky to combine in a single box. I dug deeply into what interested me at the time — a pattern that I follow today."]

   :focus
   {:label "Questions I've Been Asked"
    :items
    [{:title "Resilient System Design"
      :body "How do we make sure an agentic system understands existing business and development processes?"}
     {:title "High-Performance Systems"
      :body "Can we predict bid prices by out-performing the decentralized exchange's own intrest algorithm?"}
     {:title "Physical Science"
      :body "Is it possible to stabilize biomaterial so it can be used as paint?"}
     {:title "3D Design"
      :body "How can we get a 3D capture of this audio installation?"}
     {:title "Wetlab Processes"
      :body "Can you grow crystals out of the chemical components of black pepper?"}
     {:title "Smartcontracts"
      :body "Can we reverse engineer an entire exchange to simulate trade behavior with historic data?"}]}

   :email {:label "Email"
           :address "russell@living-systems.is"}

   :links
   [{:label "Writing"
     :href "https://solid-smash-7f1.notion.site/Posts-Directory-3c6e8016a7fa80ed9cc0f8dcc42df75d"
     :external true}
    {:label "GitHub"
     :href "https://github.com/0x43eba"
     :external true}]

   :colophon {:items ["0x43eba" "64°08′N 21°56′W"]
              :clock {:time-zone "Atlantic/Reykjavik"
                      :label "Reykjavík"}}

   :meta {:title "Russell Gill"
          :description "Russell Gill — backend and systems developer in Reykjavík, Iceland. Systems architecture, high-performance backends, infrastructure, and integrations."
          :author "Russell Gill"
          :keywords "backend developer, systems architecture, infrastructure, integrations, high performance, software engineering, Reykjavík, Iceland"
          :url "https://0x43eba.is"
          :image "/images/og.png"
          :theme-color "#131315"}})

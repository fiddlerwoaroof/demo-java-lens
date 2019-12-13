(ns com.company.main.lens-test
  (:import (com.company.main Lens Main$Relationship)
           (clojure.lang IDeref)))

(defrecord CljLens [field]
  Lens
  (over [_ value fun]
    (update value field #(fun %))))

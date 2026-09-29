const observer = new IntersectionObserver((entries) => {
    entries.forEach((entry) => {
      if (entry.isIntersecting) {
        entry.target.classList.add("visible");
        observer.unobserve(entry.target);   // animate once
      }
    });
  }, { threshold: 0.2 });

  document.querySelectorAll(".project-card").forEach((card) => observer.observe(card));
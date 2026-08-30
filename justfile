default:
	just -l

run:
	pnpm run dev

deploy:
	fly deploy

clean:
	rm -f src/*.mjs

# Regenerate the social preview card (needs: pip install Pillow)
og:
	python3 scripts/make-og.py

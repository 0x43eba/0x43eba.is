default:
	just -l

run:
	pnpm run start

deploy:
	fly deploy

# Regenerate the social preview card (needs: pip install Pillow)
og:
	python3 scripts/make-og.py

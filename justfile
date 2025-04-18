default:
	just -l

run:
	pnpm run start

deploy:
	fly deploy
#!/usr/bin/env python3
"""Generate public/images/og.png (1200x630) — the social preview card.

Reads the first frame of the avatar and sets the name/role beside it in the
same palette as the site. Re-run after changing the name or role:  just og
"""
import os
import re
import subprocess
import sys
from PIL import Image, ImageDraw, ImageFilter, ImageFont

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
AVATAR = os.path.join(ROOT, "public", "images", "avatar.gif")
OUT = os.path.join(ROOT, "public", "images", "og.png")

BG = (19, 19, 21)
INK = (236, 234, 230)
INK2 = (165, 163, 157)
ACCENT = (52, 214, 127)
RULE = (54, 54, 58)

W, H = 1200, 630
PAD = 90
AV = 300

SERIF = "/System/Library/Fonts/Supplemental/Georgia.ttf"
SANS = "/System/Library/Fonts/Supplemental/Arial.ttf"


def read_site_field(name):
    """Pull a value out of content/site.js without needing node."""
    src = open(os.path.join(ROOT, "content", "site.js")).read()
    if name == "name":
        m = re.search(r"^\s*name:\s*'([^']*)'", src, re.M)
        return m.group(1) if m else "Russell Gill"
    if name == "role":
        m = re.search(r"^\s*role:\s*\[([^\]]*)\]", src, re.M)
        if not m:
            return []
        return re.findall(r"'([^']*)'", m.group(1))
    return None


def main():
    name = read_site_field("name")
    role = read_site_field("role")

    img = Image.new("RGB", (W, H), BG)
    d = ImageDraw.Draw(img)

    # faint accent wash, mirroring the page's top-left glow
    glow = Image.new("RGB", (W, H), BG)
    ImageDraw.Draw(glow).ellipse([-360, -520, 940, 400], fill=(24, 34, 28))
    glow = glow.filter(ImageFilter.GaussianBlur(180))
    img = Image.blend(img, glow, 0.75)
    d = ImageDraw.Draw(img)

    # avatar, circular
    av = Image.open(AVATAR).convert("RGB").resize((AV, AV), Image.LANCZOS)
    mask = Image.new("L", (AV * 4, AV * 4), 0)
    ImageDraw.Draw(mask).ellipse([0, 0, AV * 4, AV * 4], fill=255)
    mask = mask.resize((AV, AV), Image.LANCZOS)
    ax, ay = PAD, (H - AV) // 2
    img.paste(av, (ax, ay), mask)
    d.ellipse([ax, ay, ax + AV, ay + AV], outline=RULE, width=2)

    tx = ax + AV + 70
    f_name = ImageFont.truetype(SERIF, 92)
    f_role = ImageFont.truetype(SANS, 26)

    name_box = d.textbbox((0, 0), name, font=f_name)
    name_h = name_box[3] - name_box[1]
    ny = H // 2 - name_h - 34
    d.text((tx, ny), name, font=f_name, fill=INK)

    ry = H // 2 + 30
    d.line([tx, ry - 26, W - PAD, ry - 26], fill=RULE, width=1)

    x = tx
    for i, part in enumerate(role):
        if i:
            d.text((x, ry), "/", font=f_role, fill=ACCENT)
            x += d.textlength("/", font=f_role) + 16
        text = part.upper()
        d.text((x, ry), text, font=f_role, fill=INK2)
        x += d.textlength(text, font=f_role) + 16

    img.save(OUT, "PNG", optimize=True)
    print(f"wrote {OUT} ({os.path.getsize(OUT) // 1024} KB)")


if __name__ == "__main__":
    main()

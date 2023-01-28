{ pkgs ? import (fetchTarball "https://github.com/NixOS/nixpkgs/archive/nixos-unstable.tar.gz") {} }:

pkgs.mkShell {
  buildInputs = [
    pkgs.jdk
    pkgs.maven
    pkgs.scenebuilder
  ];

  shellHook = ''
    echo hupsu koulu projekti
    alias compile='mvn package'
    alias run='mvn javafx:run'
    alias crun='mvn package javafx:run'
  '';


}

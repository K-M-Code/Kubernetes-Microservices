
            /// Returns the `rustc` SemVer version and additional metadata
            /// like the git short hash and build date.
            pub fn version_meta() -> VersionMeta {
                VersionMeta {
                    semver: Version {
                        major: 1,
                        minor: 71,
                        patch: 0,
                        pre: vec![semver::Identifier::AlphaNumeric("nightly".to_owned()), ],
                        build: vec![],
                    },
                    host: "x86_64-pc-windows-msvc".to_owned(),
                    short_version_string: "rustc 1.71.0-nightly (39c6804b9 2023-04-19)".to_owned(),
                    commit_hash: Some("39c6804b92aa202369e402525cee329556bc1db0".to_owned()),
                    commit_date: Some("2023-04-19".to_owned()),
                    build_date: None,
                    channel: Channel::Nightly,
                }
            }
            